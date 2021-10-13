package com.thondo.whatsappconnect.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", columnDefinition = "VARCHAR(40)")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;


    @Column(name = "STATUS")
    private String status;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CURRENT_ITEM_DONE")
    private boolean currentItemDone;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID",referencedColumnName = "ID")
    private Orders orders;

    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;


}
