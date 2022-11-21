package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Manager;
import com.mansar.schoolservice.Domain.Module;
import com.mansar.schoolservice.Repositories.ModuleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
    private ModuleRepository moduleRepository;
    private SubjectService subjectService;
    private ManagerService userService;

    public ModuleServiceImpl(@Lazy ModuleRepository moduleRepository, SubjectService subjectService, @Lazy ManagerService userService) {
        this.moduleRepository = moduleRepository;

        this.subjectService = subjectService;
        this.userService = userService;
    }

    @Override
    public Module save(Module module) {
        //Subject subject = subjectService.getSubject(module.getSubject_ID());
        //module.setSubject(subject);
        return moduleRepository.save(module);
    }

    @Override
    public Module getModuleBySubject(Long subjectID, Long id) {
        return moduleRepository.findBySubjectIDAndId(subjectID,id);
    }

    @Override
    public Module getModule(Long id) {
        return moduleRepository.findById(id).get();

    }

    @Override
    public Module update(Module module) {

        return moduleRepository.save(module);
    }

    @Override
    public void deleteModule(Long id) {
       moduleRepository.deleteById(id);
    }

    @Override
    public Collection<Module> listModule(Long id) {

        return moduleRepository.findBySubjectID(id);
    }


    @Override
    public Module addTeacher(Long teacherID, Long moduleID) {
        Manager teacher = (Manager) userService.getUser(teacherID);
        Module  module = getModule(moduleID);
        module.setManager(teacher);
        return update(module);

    }
}
