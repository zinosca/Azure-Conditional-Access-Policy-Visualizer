package azure.scenehandler;
/**
 * Most basic interface for beeing an observer
 * @author Andy Bui
 *
 */
public interface IsObserver {
	/**
	 * This method is always called when an observed object changes
	 */
	void update();
}
