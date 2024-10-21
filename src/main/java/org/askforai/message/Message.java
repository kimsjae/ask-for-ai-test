package org.askforai.message;

import java.time.LocalDateTime;

import org.askforai.chatRoom.ChatRoom;
import org.askforai.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom; // 해당 메시지가 속한 채팅방

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 메시지를 보낸 사용자

    @Column(nullable = false)
    private String content; // 메시지 내용

    @Column(nullable = false)
    private LocalDateTime timestamp; // 메시지 발송 시간
}