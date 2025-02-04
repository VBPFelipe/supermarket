package br.com.notification.config;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.model.NotificationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public record ConvertUtils<T> (ModelMapperConfiguration modelMapperConfiguration) {

    public T convertRequestToEntity (T request, Class<T> type) {
        return modelMapperConfiguration.modelMapper().map(request, type);
    }

    public T convertEntityToResponse(T entity, Class<T> type) {
        return modelMapperConfiguration.modelMapper().map(entity, type);
    }

    public List<T> convertToListResponse(List<T> responses, Class<T> type) {
        return responses
                .stream()
                .map(response -> new ModelMapper().map(response, type))
                .collect(Collectors.toList());
    }
}
