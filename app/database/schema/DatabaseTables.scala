package database.schema

import slick.lifted.TableQuery

object DatabaseTables {
  lazy val addressTable: TableQuery[AddressTable] = TableQuery[AddressTable]
  lazy val doctorSpecialityTable: TableQuery[DoctorSpecialityTable] = TableQuery[DoctorSpecialityTable]
  lazy val serviceTable: TableQuery[ServiceTable] = TableQuery[ServiceTable]
  lazy val specialityTable: TableQuery[SpecialityTable] = TableQuery[SpecialityTable]
  lazy val userTable: TableQuery[UserTable] = TableQuery[UserTable]
}
