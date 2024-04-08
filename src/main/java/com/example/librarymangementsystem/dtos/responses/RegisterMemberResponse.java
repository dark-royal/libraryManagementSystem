package com.example.librarymangementsystem.dtos.responses;

import com.example.librarymangementsystem.data.models.Member;
import lombok.Data;
@Data
public class RegisterMemberResponse  {
    private String message;
    private Long userID;
}
