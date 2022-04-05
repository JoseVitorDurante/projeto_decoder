package com.ead.authuser.dtos;

import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//tudo o que for null nao devolve a variavel no json
public class UserDTO {

    public interface UserView {
        public static interface RegistrationPost {
        }

        public static interface UserPut {
        }

        public static interface PasswordPut {
        }

        public static interface ImagePut {
        }
    }

    //userView utilizado para as validações e atualização que serão consideradas do lado do endPoint utilizando o @JsonView(UserDTO.UserView.XXXX.class).
    // Seria como uma visão ira olhar somente para as variaveis anotadas

    private UUID userId;

    @NotBlank(groups = UserView.RegistrationPost.class)//so sera validado ao utlizar o UserView.RegistrationPost
    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)//so sera validado ao utlizar o UserView.RegistrationPost
    @UsernameConstraint(groups = UserView.RegistrationPost.class)//criada a anotação
    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = {UserView.PasswordPut.class, UserView.RegistrationPost.class})
    @Size(min = 4, max = 20, groups = {UserView.PasswordPut.class, UserView.RegistrationPost.class})
    @JsonView({UserView.PasswordPut.class, UserView.RegistrationPost.class})
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min = 4, max = 20, groups = {UserView.PasswordPut.class})
    @JsonView({UserView.PasswordPut.class})
    private String oldPassword;

    @JsonView({UserView.UserPut.class, UserView.RegistrationPost.class})
    private String fullName;

    @JsonView({UserView.UserPut.class, UserView.RegistrationPost.class})
    private String phoneNumber;

    @JsonView({UserView.UserPut.class, UserView.RegistrationPost.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView({UserView.ImagePut.class})
    private String imageUrl;
}
