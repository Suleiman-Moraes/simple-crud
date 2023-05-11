package br.com.simplecrud.api.model.dto.v1;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicTokenListDTO implements Serializable {
    
    private String username;

    private boolean enabled;
}
