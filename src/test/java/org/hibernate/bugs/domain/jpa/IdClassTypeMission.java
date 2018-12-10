package org.hibernate.bugs.domain.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(IdClassTypeMissionId.class)
public class IdClassTypeMission implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "agent_login", referencedColumnName = "login", foreignKey = @ForeignKey(name = "fk_id_class_type_mission_agent"))
	private Agent agent;

	private LocalDateTime dateEnd;

	private LocalDateTime dateStart;

	private String city;

}
