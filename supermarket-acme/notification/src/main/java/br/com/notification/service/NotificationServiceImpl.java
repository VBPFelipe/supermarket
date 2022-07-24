package br.com.notification.service;

import br.com.notification.config.ConvertUtils;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.model.NotificationEntity;
import br.com.notification.repository.NoticationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NoticationRepository noticationRepository;

    private final ConvertUtils convertUtils;

    public NotificationServiceImpl(NoticationRepository noticationRepository, ConvertUtils convertUtils) {
        this.noticationRepository = noticationRepository;
        this.convertUtils = convertUtils;
    }


    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        var notificationEntity = (NotificationEntity) this.convertUtils.convertRequestToEntity(request, NotificationEntity.class);
        var entity =  this.noticationRepository.save( notificationEntity );
        return (NotificationResponse) this.convertUtils.convertEntityToResponse( entity, NotificationResponse.class);
    }

    @Override
    public NotificationResponse getNotification(Long notificationId) {
        if(notificationId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return (NotificationResponse) this.convertUtils.convertEntityToResponse(
                                                        this.noticationRepository.findById(notificationId),
                                                        NotificationResponse.class
                                        );
    }

    @Override
    public List<NotificationResponse> listAll() {
        List<NotificationEntity> entities = this.noticationRepository.findAll();
        List<NotificationResponse> responses = (List<NotificationResponse>) this.convertUtils.convertToListResponse(entities, NotificationResponse.class);
        return responses;
    }

    @Override
    public NotificationResponse updateNotification(Long notificationId, NotificationRequest request) {
        if(notificationId == null || request == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        var entity = (NotificationEntity) this.convertUtils.convertRequestToEntity(request, NotificationEntity.class);
        entity.setId(notificationId);
        return (NotificationResponse) this.convertUtils.convertEntityToResponse(
                                            this.noticationRepository.save(entity),
                                            NotificationResponse.class
                                        );
    }

    @Override
    public void deleteNotification(Long notificationId) {
        this.noticationRepository.deleteById(notificationId);
    }
}
