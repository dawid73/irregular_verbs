package cloud.dawid.irregular.irregular;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Verb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String infinitive;

    private String pastsimple;

    private String pastparticiple;

    private String tlumaczenie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getPastsimple() {
        return pastsimple;
    }

    public void setPastsimple(String pastsimple) {
        this.pastsimple = pastsimple;
    }

    public String getPastparticiple() {
        return pastparticiple;
    }

    public void setPastparticiple(String pastparticiple) {
        this.pastparticiple = pastparticiple;
    }

    public String getTlumaczenie() {
        return tlumaczenie;
    }

    public void setTlumaczenie(String tlumaczenie) {
        this.tlumaczenie = tlumaczenie;
    }
}
