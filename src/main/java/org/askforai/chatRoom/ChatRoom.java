package org.askforai.chatRoom;


import java.util.List;

import org.askforai.favoriteChatRoom.FavoriteChatRoom;
import org.askforai.message.Message;
import org.askforai.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 채팅방 이름

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 채팅방의 주인 (선택적)

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Message> messages; // 이 채팅방에 속한 메시지 목록

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<FavoriteChatRoom> favoriteChatRooms; // 이 채팅방을 즐겨찾기한 사용자 목록
}