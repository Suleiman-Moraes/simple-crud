package br.com.simplecrud.api.model;

import java.io.Serializable;

import br.com.simplecrud.api.model.interfaces.IModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "basic_token")
public class BasicToken implements Serializable, IModel<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;
    
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private boolean enabled;
}
