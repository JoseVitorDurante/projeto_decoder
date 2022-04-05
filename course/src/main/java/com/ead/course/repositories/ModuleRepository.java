package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {

//    @EntityGraph(attributePaths = {"course"})//dessa forma mesmo a variavel course estando como LAZZY ele ira trazer ela como se estivesse EAGER/ sera carragado junto com a entidade mesmo sendo LAZZY
//    ModuleModel findByTitle(String title);


//    @Modifying //alem de apenas consultar ira poder modificar no banco com querys customizadas utlizando do update, delete ...
//    @Query(value = "delete from tb_modules where course_course_id = :courseId", nativeQuery = true)
//    void delete(@Param("courseId") UUID courseId);


    @Query(value = "select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    List<ModuleModel> finAllLModulesIntoCourse(@Param("courseId") UUID courseId);

    @Query(value = "select * from tb_modules where course_course_id = :courseId and module_id = :moduleId", nativeQuery = true)
    Optional<ModuleModel> findModuleIntoCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}
