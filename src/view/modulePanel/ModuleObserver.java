package view.modulePanel;

import java.util.Collection;
import java.util.Map;


public interface ModuleObserver {
    public void update (Map<String, Collection<ModuleData>> moduleMap, String variableUpdateError);
}
