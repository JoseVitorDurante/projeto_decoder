package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor //construtor com todos os parametros
@NoArgsConstructor //construtor nenhum parametros
@JsonInclude(JsonInclude.Include.NON_NULL)//tudo o que for null nao devolve a variavel no json
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID userId;

}
