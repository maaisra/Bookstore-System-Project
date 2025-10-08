package Lib.ObserverPattern;

import Lib.OrderProcessor.*;

public interface OrderObserver{
    void update(Order order);
}
