package azure.scenehandler;
/**
 * Most basic interface for observing an object
 * @author Andy Bui
 *
 */
public interface IsObservable {

	/**
	 * Add an observer that listens for updates
	 * @param observer
	 */
	void addListener(String name, IsObserver observer);

	/**
	 * Remove an observer from the list
	 * @param observer
	 */
	void removeListener(IsObserver observer);
}
