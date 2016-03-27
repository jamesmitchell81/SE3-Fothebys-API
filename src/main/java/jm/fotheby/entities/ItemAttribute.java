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

  public void setValue(T value) { this.value = value; }
  public T getValue() { return this.value; }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }
}