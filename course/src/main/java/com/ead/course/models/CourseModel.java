package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//tudo o que for null nao devolve a variavel no json
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss'Z'")
    private LocalDateTime creationData;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdateDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus courseStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)//define o relacimanento pela chave estrangeira. cascade = CascadeType.ALL, orphanRemoval = true delega para a JPA deletar os filhos
    @Fetch(FetchMode.SUBSELECT)//como fara o select
//    @OnDelete(action = OnDeleteAction.CASCADE)//deletar em castata delega para o banco deletar
    private Set<ModuleModel> modules;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseUserModel> courseUsers;

    public CourseUserModel convertToCourseUserModel(UUID userId){
        return new CourseUserModel(null, userId, this);
    }
}
