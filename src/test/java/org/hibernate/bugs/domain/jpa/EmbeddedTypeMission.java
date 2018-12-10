package org.hibernate.bugs.domain.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmbeddedTypeMission implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmbeddedTypeMissionId id;

	@MapsId("agentLogin")
	@ManyToOne(optional = false)
	@JoinColumn(name = "agent_login", referencedColumnName = "login", foreignKey = @ForeignKey(name = "fk_embedded_type_mission_agent"))
	private Agent agent;

	private LocalDateTime dateEnd;

	private LocalDateTime dateStart;

	private String city;

	public EmbeddedTypeMission() {
		super();
		this.id = new EmbeddedTypeMissionId();
	}

}
