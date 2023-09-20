package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entities.Staff;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
//     @Query(value = "SELECT * FROM staff s WHERE s.email_id= :emailId",nativeQuery = true)
     Staff findByEmailId(@PathParam("emailId") String emailId);

}
