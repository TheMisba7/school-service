package com.mansar.schoolservice.Services;

import com.mansar.schoolservice.Domain.Subject;
import com.mansar.schoolservice.Exceptions.SubjectNotFoundException;
import com.mansar.schoolservice.Repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
   private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(id));

    }

    @Override
    public Subject update(Subject subject) {

        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Collection<Subject> listSubjects() {
        return subjectRepository.findAll();
    }
}
