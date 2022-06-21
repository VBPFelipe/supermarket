package br.com.notification.repository;

import br.com.notification.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticationRepository extends JpaRepository<NotificationEntity, Long> {
}
