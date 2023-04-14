package br.com.simplecrud.api.model.dto.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private Long key;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}
