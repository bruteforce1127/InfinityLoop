package com.kucp1127.KidsLearningApp.Configuration.Repository;


import com.kucp1127.KidsLearningApp.Configuration.Model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParentRepository extends JpaRepository<Parents,String> {

}