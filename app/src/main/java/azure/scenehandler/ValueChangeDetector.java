package azure.scenehandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The ValueChangeDetector class implements the IsObservable interface and provides a mechanism for detecting
 * changes to a collection of items and notifying registered observers.
 */
public class ValueChangeDetector implements IsObservable {

    /**
     * A map of registered observers keyed by name.
     */
    private Map<String, IsObserver> listener = new HashMap<>();

    /**
     * A map of items keyed by name.
     */
    private Map<String, String> items = new HashMap<>();

    /**
     * Adds a new observer to the map of registered observers.
     *
     * @param name     the name of the observer to add.
     * @param observer the observer to add.
     */
    @Override
    public void addListener(String name, IsObserver observer) {
        listener.put(name, observer);
    }

    /**
     * Removes an observer from the map of registered observers.
     *
     * @param observer the observer to remove.
     */
    @Override
    public void removeListener(IsObserver observer) {
        listener.remove(observer);
    }

    /**
     * Notifies the observer with the given name that a change has occurred.
     *
     * @param name the name of the observer to notify.
     */
    public void informListener(String name) {
        if (listener.size() > 0) {
            for (Map.Entry<String, IsObserver> entry : listener.entrySet()) {
                if (entry.getKey().equals(name)) {
                    entry.getValue().update();
                }
            }
        }
    }

    /**
     * Sets the value of an item with the given key.
     *
     * @param key  the key of the item to set.
     * @param item the value of the item to set.
     */
    public void setItem(String key, String item) {
        items.put(key, item);
    }

    /**
     * Gets the value of the item with the given key.
     *
     * @param key the key of the item to get.
     * @return the value of the item, or null if the key is not found.
     */
    public String getItem(String key) {
        if (items.size() > 0) {
            for (Map.Entry<String, String> entry : items.entrySet()) {
                if (entry.getKey().equals(key)) {
                    return items.get(key);
                }
            }
        }
        return "0";
    }
}

