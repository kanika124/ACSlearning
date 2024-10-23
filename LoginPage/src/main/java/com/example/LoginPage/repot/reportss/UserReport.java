package com.example.LoginPage.repot.reportss;

import com.example.LoginPage.user.entity.User;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserReport {
        @Autowired
        private UserRepo userRepo;
        public Map<String, List<String>> MyListMap(Map<Integer,String> map){
            Map<String, List<String>> map1=new HashMap<>();
            List<User> listproduct=userRepo.findAll();
            List<String> ls=new ArrayList<>();
            List<String> ls1=new ArrayList<>();

            for (Map.Entry<Integer,String> entry : map.entrySet()) {
                if(entry.getValue().equals("name")) {
                    for (int i = 0; i < listproduct.size(); i++) {
                        ls.add(listproduct.get(i).getName());
                    }
                    map1.put(entry.getValue(),ls);
                } else if (entry.getValue().equals("phone")) {
                    for(int i=0;i<listproduct.size();i++){
                        ls1.add(String.valueOf(listproduct.get(i).getPhoneNo()));
                    }
                    map1.put(entry.getValue(),ls1);
                }


            }

            return map1;

        }
}

