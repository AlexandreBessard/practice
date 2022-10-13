package designPatterns.creationalDesignPattern.builders.fluent_builder_inheritance;

class PersonBuilder<SELF extends PersonBuilder<SELF>>
{
  protected Person person = new Person();

  // critical to return SELF here
  public SELF withName(String name)
  {
    person.name = name;
    return self();
  }

  protected SELF self()
  {
    // unchecked cast, but actually safe
    // proof: try sticking a non-PersonBuilder
    //        as SELF parameter; it won't work!
    return (SELF) this;
  }

  public Person build()
  {
    return person;
  }
}