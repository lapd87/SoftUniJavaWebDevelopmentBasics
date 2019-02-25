package panda.repository.interfaces;

import panda.domain.entities.Package;
import panda.domain.entities.Status;

import java.util.Optional;

public interface PackageRepository extends GenericRepository<Package, String> {

    Optional findAllPackagesByStatus(Status status);
}
