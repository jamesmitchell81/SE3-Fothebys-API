package jm.fotheby.entities;

public class ItemAttribute<T>
{
  private String name;
  private T value;

  public ItemAttribute(String name, T value)
  {
    this.name = name;
    this.value = value;
  }

  private void setValue(T value) { this.value = value; }
  private T getValue() { return this.value; }

  private void setName(String name) { this.name = name; }
  private String getName() { return this.name; }
}