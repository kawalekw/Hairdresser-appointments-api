package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author mateusz
 */
@Entity
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String name;
    @Getter
    private String phone;
    private boolean blocked;
    
    public Client(String name, String phone){
        this(0, name, phone, false);
    }
        
}
