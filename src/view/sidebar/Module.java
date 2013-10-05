package view.sidebar;

import java.util.List;


/**
 * @author susanzhang93
 * 
 */
public abstract class Module {
    private List<ModuleData> Library;

    /**
     * converts Model's output of command data to a ModuleData
     * representation
     * 
     * @return List of created ModuleData
     */
    protected abstract List<ModuleData> initializeModuleContents ();

    protected abstract void click ();

}
