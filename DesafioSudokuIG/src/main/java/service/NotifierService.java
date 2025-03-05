package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService {

    private Map<EventEnum, List<EventListener>> listeners= new HashMap<>(){{
        put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    public  void subscriber(final EventEnum eventEnum, EventListener listener){
       List<EventListener> selectedListeners = listeners.get(eventEnum);
       selectedListeners.add(listener);

    }
    public void notify(final EventEnum eventEnum){
        listeners.get(eventEnum).forEach(l-> l.update(eventEnum));
    }
}
