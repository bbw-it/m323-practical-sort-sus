package model;

import java.util.UUID;

public class Haustier {
  private UUID id;
  private String name;
  private String tierart;

  public Haustier(UUID id, String name, String tierart) {
    this.id = id;
    this.name = name;
    this.tierart = tierart;
  }

  // Getter
  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTierart() {
    return tierart;
  }

  // Setter
  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTierart(String tierart) {
    this.tierart = tierart;
  }

  // toString
  @Override
  public String toString() {
    return "Haustier{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", tierart='" + tierart + '\'' +
      '}';
  }
}
