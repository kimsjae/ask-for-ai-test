package org.askforai.user;

import java.util.List;

import org.askforai.chatRoom.ChatRoom;
import org.askforai.favoriteChatRoom.FavoriteChatRoom;
import org.askforai.message.Message;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms; // 이 사용자가 소속된 채팅방 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages; // 이 사용자가 보낸 메시지 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteChatRoom> favoriteChatRooms; // 즐겨찾기 목록
    
    // 회원가입
    public User(UserRequest.RegisterDTO reqDTO) {
    	this.username = reqDTO.getUsername();
    	this.password = reqDTO.getPassword();
    	this.email = reqDTO.getEmail();
    }
}