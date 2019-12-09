package com.direction.common.util;

import org.modelmapper.ModelMapper;

import com.google.gson.Gson;


public class SingletonFactory {

	private SingletonFactory() {
	}

	private static class LoaderGson {
//		private static final Gson GSON_INSTANCE = new GsonBuilder()
//	             .disableHtmlEscaping()
//	             .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//	             .setPrettyPrinting()
//	             .serializeNulls()
//	             .create();
		private static final Gson GSON_INSTANCE = new Gson();
		private static final ModelMapper MODELMAPPER_INSTANCE = new ModelMapper();
	}

	public static Gson getGsonInstance() {
		return LoaderGson.GSON_INSTANCE;
	}

	public static ModelMapper getModelMapperInstance() {
		return LoaderGson.MODELMAPPER_INSTANCE;
	}

}
