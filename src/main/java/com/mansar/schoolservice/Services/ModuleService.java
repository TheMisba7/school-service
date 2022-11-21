package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Module;

import java.util.Collection;

public interface ModuleService{

    //TODO : CRUD operations
    Module save(Module module);
    Module getModuleBySubject(Long subjectID,Long id);
    Module getModule(Long id);
    Module update(Module module);
    void deleteModule(Long id);
    Collection<Module> listModule(Long id);
    Module addTeacher(Long teacherID,Long moduleID);
}
