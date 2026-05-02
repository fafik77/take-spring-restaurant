package com.example.demo.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

@Converter
public class JsonStringListConverter implements AttributeConverter<List<String>, String> {
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<String> list) {
		// Map null/empty list to null or the JSON string "[]"
		if (list == null) return null;
		try {
			return mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) return null;
		try {
			var res = mapper.readValue(dbData, new TypeReference<List<String>>() {
			});
			if (res.isEmpty()) return null;
			return res;
		} catch (IOException e) {
			return null;
		}
	}
}