package services

import database.dao.{MedicoDao, ServiceDao, SpecialityDao}
import javax.inject.Inject
import models.{MedicoWithServicesAndSpecialities, UserWithAddresses}

import scala.concurrent.{ExecutionContext, Future}

class MedicoService @Inject()(
  doctorDao: MedicoDao,
  serviceDao: ServiceDao,
  specialityDao: SpecialityDao
)(implicit ec: ExecutionContext) {

  def getDoctors(name: Option[String]): Future[Seq[UserWithAddresses]] =
    doctorDao.getMedicos(name)

  def getMedicoWithServicesAndSpecialities(
    doctorId: Long
  ): Future[Option[MedicoWithServicesAndSpecialities]] = {
    for {
      userEntity <- doctorDao.getMedicoById(doctorId)
      services <- serviceDao.getServicesByDoctorId(doctorId)
      specialities <- specialityDao.getSpecialitiesByDoctorId(doctorId)
    } yield
      userEntity.map(
        u => MedicoWithServicesAndSpecialities(u.toUser, services, specialities)
      )
  }
}
