package org.ftd.educational.mvc.cmds;

/**
 *
 * @author ftdippold
 */
public class Rule {
    private Long id;
    private String name;

    public Rule(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }
    
    public Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }    
    
}
