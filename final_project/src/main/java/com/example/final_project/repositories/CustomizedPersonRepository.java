package com.example.final_project.repositories;
import java.util.*;

import com.example.final_project.dto.PersonInfo;



interface CustomizedPersonRepository {
    List<PersonInfo> findPersonInfoList();

}