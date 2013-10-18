package view;

import java.util.Collection;
import java.util.Map;
import view.modulePanel.ModuleData;


public interface Observer {

    public void update (String error,
                        String updateVariable,
                        Map<String, Collection<ModuleData>> moduleMap);

}
