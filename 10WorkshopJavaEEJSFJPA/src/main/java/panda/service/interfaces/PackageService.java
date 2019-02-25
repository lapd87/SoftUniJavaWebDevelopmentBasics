package panda.service.interfaces;

import panda.domain.entities.Status;
import panda.domain.models.service.PackageServiceModel;

import java.util.List;
import java.util.Optional;

public interface PackageService {

    boolean packageCreate(PackageServiceModel packageServiceModel);

    List<PackageServiceModel> findAllPackagesByStatus(Status status);

    boolean packageStatusChange(String id);

    Optional<PackageServiceModel> findPackageById(String id);
}
