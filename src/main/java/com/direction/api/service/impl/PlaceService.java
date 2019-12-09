package com.direction.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.direction.api.consts.GlobalConsts;
import com.direction.api.consts.ResultCodeConsts;
import com.direction.api.model.EdgeModel;
import com.direction.api.model.LegModel;
import com.direction.api.model.MapModel;
import com.direction.api.model.RequestDirectionModel;
import com.direction.api.model.ResponseModel;
import com.direction.api.model.RouteModel;
import com.direction.api.service.IPlaceService;
import com.direction.common.constants.TableName;
import com.direction.common.entity.PlaceEntity;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.SingletonFactory;
import com.direction.common.util.Util;
import com.google.gson.Gson;
import com.mysql.cj.fabric.xmlrpc.base.Array;

@Service
public class PlaceService extends AbstractBasicService implements IPlaceService{

	private static final Logger log= Logger.getLogger(PlaceService.class);
	private Gson gson = SingletonFactory.getGsonInstance();
	@Override
	public ResponseModel getListPlace() {
		ResponseModel resp = new ResponseModel();
		try {
			List<PlaceEntity> list = placeDAO.findAll(GlobalConsts.MAX_RECORD);
			if(list != null){
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(list);
			}
			else{
				resp.setCode(ResultCodeConsts.DATA_NOT_FOUND.getCode());
			}
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}

	@Override
	public ResponseModel getRandomPlace(Integer quality) {
		ResponseModel resp = new ResponseModel();
		try {
			List<PlaceEntity> list = placeDAO.findAll(GlobalConsts.MAX_RECORD);
			int size = list.size();
			List<PlaceEntity> listPlace = new ArrayList<>();
			List<Integer> indexDone = new ArrayList<>();
			for (int i = 0; i < quality; i++) {
				int index = getRandomNumberInRange(1, size-1);
				while(!checkExist(indexDone, index)){
					index = getRandomNumberInRange(1, size-1);
				}
				PlaceEntity place = placeDAO.findById(index);
				listPlace.add(place);
				indexDone.add(index);
			}
			
			if(listPlace != null){
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(listPlace);
			}
			else{
				resp.setCode(ResultCodeConsts.DATA_NOT_FOUND.getCode());
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(CommonUtil.convertExceptionToString(e));
		}
		
		return resp;
	}
	
	private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@Override
	public ResponseModel getApiTest() {
		ResponseModel resp = new ResponseModel();
		try{
			//https://maps.googleapis.com/maps/api/directions/json?origin=10.8070183, 106.6955244&destination=Big+C+Trường+Chinh,+Trường+Chinh,+Tân+Phú,+Ho+Chi+Minh+City&key=AIzaSyA6cDU_pln3LpvcypA3AENSDoxP3lfCYGA&language=vi
			String start = "10.8070183,106.6955244";
			String end = "Big+C+Trường+Chinh,+Trường+Chinh,+Tân+Phú,+Ho+Chi+Minh+City";
			StringBuilder sb = new StringBuilder();
			sb.append("https://maps.googleapis.com/maps/api/directions/json?origin=").append(start);
			sb.append("&destination=").append(end);
			sb.append("&key=AIzaSyA6cDU_pln3LpvcypA3AENSDoxP3lfCYGA&language=vi");
			String Url = sb.toString();
			String result = Util.AccessUrlToString(Url);
			MapModel mapModel = gson.fromJson(result, MapModel.class);
			if(mapModel.getStatus().equals("OK")){
				EdgeModel data = new EdgeModel();
				data.setDistance(mapModel.getRoutes().get(0).getLegs().get(0).getDistance().getValue());
				data.setDuration(mapModel.getRoutes().get(0).getLegs().get(0).getDuration().getValue());
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(data);
			}
			else{
				resp.setCode(ResultCodeConsts.ERROR_GET_ACCESS_DIRECTION.getCode());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		
		return resp;
	}

	@Override
	public ResponseModel GTS(RequestDirectionModel model) {
		ResponseModel resp = new ResponseModel();
		try {
			
			//String start = model.getLatitudeStart()+","+model.getLatitudeStart();
			PlaceEntity start = new PlaceEntity();
			start.setLatitude(model.getLatitudeStart());
			start.setLongitude(model.getLongitudeStart());
			List<PlaceEntity> list = model.getData();
			list.add(start);
			List<RouteModel> routes = new ArrayList<>();
			int size = list.size();
			
			routes.add(getRouteByFilter(start, list, size, 1));
			routes.add(getRouteByFilter(start, list, size, 2));
			
			
			resp.setCode(ResultCodeConsts.SUCCESS.getCode());
			resp.setData(routes);
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}
	
	private RouteModel getRouteByFilter(PlaceEntity start,List<PlaceEntity> list,int size,int filter){
		RouteModel resp = new RouteModel();
		try{
			EdgeModel array[][] = new EdgeModel[size][size];
		
			for (int i = list.size() -1 ; i >= 0; i--) {
				for (int j = list.size() -2; j >= 0; j--) {
					if(i != j){
						EdgeModel d = calcute2point(list.get(i), list.get(j), filter);
						array[(list.size() -1) - i][(list.size() -1) - j] = d;
					}
						
				}
			}
			
//			for(int i = 0 ; i < list.size();i++){
//				for (int j = 0; j < list.size(); j++) {
//					if(array[i][j] != null)
//						System.out.print("("+array[i][j].getDistance()+","+array[i][j].getDuration()+") ");
//					else
//						System.out.print("(-,-)");
//				}
//				System.out.println();
//			}
			
			RouteModel kqDistance = GTS_Route(array, size, 1);
			RouteModel kqDuration = GTS_Route(array, size, 2);
			if(filter == 1){
				if(kqDistance.getDistance() < kqDuration.getDistance())
					resp = kqDistance;
				else
					resp = kqDuration;
			}
			else{
				if(kqDistance.getDuration() < kqDuration.getDuration())
					resp = kqDistance;
				else
					resp = kqDuration;
			}
			resp.setFilter(filter);
			
		}catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp = null;
		}
		return resp;
	}
	private RouteModel GTS_Route(EdgeModel array[][],int size,int filter){
		RouteModel resp = new RouteModel();
		try {
			List<Integer> indexDone = new ArrayList<>();
			indexDone.add(0);
			List<LegModel> legs= new ArrayList<>();
			int n = 0;
			long distance = 0;
			long duration = 0;
//			System.out.print(n +"->");
			while(indexDone.size() < size){
				int m = n;
				n = findMin(array, size, n, indexDone, filter);
				indexDone.add(n);
				legs.add(array[m][n].getLeg());
				distance += array[m][n].getLeg().getDistance().getValue();
				duration += array[m][n].getLeg().getDuration().getValue();
				//System.out.print(n +"->");
				
			}	
//			System.out.println("distance"+distance);
//			System.out.println("duration"+duration);
//			System.out.println("=================================");
			resp.setLegs(legs);
			resp.setDistance(distance);
			resp.setDuration(duration);
			resp.setFilter(filter);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(CommonUtil.convertExceptionToString(e));
		}
		return resp;
		
	}
	
	private List<LegModel> APlus(EdgeModel array[][],Double Pdistance[][],int goal,int size){
		List<LegModel> legs = new ArrayList<>();
		List<Integer> indexDone = new ArrayList<>();
		indexDone.add(0);
		int n = 0;
		List<EdgeModel> g = new ArrayList<EdgeModel>();
		String[] route = new String[size*size];
		while(indexDone.size() < size){
			int m =n;
			int start = 2;
			EdgeModel min = array[m][1];
			Double mind = Pdistance[m][1];
			if(min != null){
				min.setEnd(m);
			}
			else{
				min = array[m][2];
				mind = Pdistance[m][2];
				start = 3;
			}
			Double fmin = min.getDistance().doubleValue() + mind;
			g.add(min);
			for(int i = start ; i < size ; i++){
				if(checkExist(indexDone, i) && fmin > (array[m][i]).getDistance() + Pdistance[m][i]){
					fmin = array[m][i].getDistance() + Pdistance[m][i];
				}
			}
		}
		
		return legs;
	}
	
	private boolean checkExist(List<Integer> indexDone,int i){
		for (int j = 0; j < indexDone.size(); j++) {
			if(indexDone.get(j) == i){
				return false;
			}
		}
		return true;
	}
	
	private int findMin(EdgeModel[][] array,int size, int n,List<Integer> indexDone,int filter){
		Long min = Long.MAX_VALUE; 
		int index = -1;
		if(filter == 1){
			for(int i = 1 ; i < size;i++){
				if(checkExist(indexDone, i) && array[n][i].getDistance()<min){
					min = array[n][i].getDistance();
					index = i;
				}
			}
			
		}
		else{
			for(int i = 1 ; i < size;i++){
				if(checkExist(indexDone, i) && array[n][i].getDuration()< min){
					min = array[n][i].getDuration();
					index = i;
				}
			}
		}
		
		return index;
	}
	private EdgeModel calcute2point(PlaceEntity first, PlaceEntity second,Integer filter){
		String start,end ;
		if(first.getLatitude() != null && first.getLongitude() != null){
			start = first.getLatitude() +","+first.getLongitude();
		}
		else{
			start = first.getAddress();
			start =  start.trim();
			start = start.replace(" ", "+");
		}
		
		if(second.getLatitude() != null && second.getLongitude() != null){
			end = second.getLatitude() +","+second.getLongitude();
		}
		else{
			end = second.getAddress();
			end =  end.trim();
			end = end.replace(" ", "+");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://maps.googleapis.com/maps/api/directions/json?origin=").append(start);
		sb.append("&destination=").append(end);
		sb.append("&alternatives=true&key=AIzaSyA6cDU_pln3LpvcypA3AENSDoxP3lfCYGA&language=vi");
		String Url = sb.toString();
		//log.debug(Url);
		String result = Util.AccessUrlToString(Url);
		MapModel mapModel = gson.fromJson(result, MapModel.class);
		//log.debug(mapModel.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getManeuver());
		if(mapModel.getStatus() != null &&mapModel.getStatus().equals("OK")){
			EdgeModel data = new EdgeModel();
			List<RouteModel> listRoute= mapModel.getRoutes();
			Long minDistance = listRoute.get(0).getLegs().get(0).getDistance().getValue();
			Long minDuration = listRoute.get(0).getLegs().get(0).getDuration().getValue();
			int index = 0;
			for (int i = 1; i < listRoute.size();i++) {
				if(filter == 1){
					Long test = listRoute.get(i).getLegs().get(0).getDistance().getValue();
					if(test < minDistance){
						minDistance = listRoute.get(i).getLegs().get(0).getDistance().getValue();
						index = i;
					}
						
				}
				else{
					if(listRoute.get(i).getLegs().get(0).getDuration().getValue() < minDuration){
						minDuration = listRoute.get(i).getLegs().get(0).getDuration().getValue();
						index = i;
					}
						
				}
			}
			data.setDistance(listRoute.get(index).getLegs().get(0).getDistance().getValue());
			data.setDuration(listRoute.get(index).getLegs().get(0).getDuration().getValue());
			
			LegModel leg = new LegModel();
			leg.setDistance(listRoute.get(index).getLegs().get(0).getDistance());
			leg.setDuration(listRoute.get(index).getLegs().get(0).getDuration());
			leg.setEnd_address(listRoute.get(index).getLegs().get(0).getEnd_address());
			leg.setEnd_location(listRoute.get(index).getLegs().get(0).getEnd_location());
			leg.setOverview_polyline(listRoute.get(index).getOverview_polyline());
			leg.setStart_address(listRoute.get(index).getLegs().get(0).getStart_address());
			leg.setStart_location(listRoute.get(index).getLegs().get(0).getStart_location());
			leg.setSteps(listRoute.get(index).getLegs().get(0).getSteps());
			
			data.setLeg(leg);
			data.setOverview_polyline(listRoute.get(index).getOverview_polyline());
			return data;
		}
		else{
			return null;
		}
		
	}
	
	
}
