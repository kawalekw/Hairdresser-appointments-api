package com.javaretards.hairdresserapponintments.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author mateusz
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String phone;
    private boolean blocked;

    public Client(String phone) {
        this.phone = phone;
        this.blocked = false;
    }
    
    public void block(){
        this.blocked=true;
    }
    
    public void unlock(){
        this.blocked=false;
    }
}
