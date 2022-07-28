package com.example.tacos.monitor;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.Taco;

@Service
@ManagedResource
public class TacoCounter
        extends AbstractRepositoryEventListener<Taco>
implements NotificationPublisherAware {

    private AtomicLong counter;
    private NotificationPublisher notificationPublisher;

    @Autowired
    public TacoCounter(TacoRepository tacoRepository)
    {
        long currentTacoCount = tacoRepository.count();

        counter = new AtomicLong(currentTacoCount);
    }

    @Override
    protected void onAfterCreate(Taco entity) {
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getTacoCount()
    {
        return counter.get();
    }

    @ManagedOperation
    public long increment(long delta)
    {
        long before = counter.get();

        long after = counter.addAndGet(delta);

        if (after / 100 > before / 100)
        {
            notificationPublisher.sendNotification(
                new Notification("taco.count", this, before, after + " tacos created")
            );
        }

        return after;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        
        this.notificationPublisher = notificationPublisher;
        
    }
    
}
