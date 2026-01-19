package com.example.apollum.service.implementation;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.contracts.*;
import com.example.apollum.models.error.AppException;
import com.example.apollum.models.error.MessageConstants;
import com.example.apollum.models.error.SysException;
import com.example.apollum.models.type.AppointmentStatusType;
import com.example.apollum.repository.AppointmentRepository;
import com.example.apollum.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public CreateAppointmentResponse create(CreateAppointmentRequest request) {
        if (request.title() == null || request.title().isBlank()) {
            throw new AppException(
                    MessageConstants.TITLE_MUST_NOT_BE_BLANK,
                    "Please specify a valid title to proceed");
        }

        if (request.startTime().isBefore(LocalDateTime.now())
                || request.endTime().isBefore(LocalDateTime.now())) {
            throw new AppException(
                    MessageConstants.SLOT_INVALID,
                    "Please specify startTime / endTime in future");
        }

        if (request.startTime().isAfter(request.endTime())) {
            throw new AppException(
                    MessageConstants.SLOT_INVALID,
                    "Please select endDate ahead of startDate");
        }

        Appointment appointment = new Appointment();
        appointment.setReason(request.title());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setStatus(AppointmentStatusType.SCHEDULED);

        try {
            appointmentRepo.save(appointment);
        } catch (Exception ex) {
            throw new SysException("Internal Server Error", ex.getMessage());
        }

        return new CreateAppointmentResponse(
                appointment.getId().toString()
        );
    }

    @Override
    public GetAppointmentResponse get(UUID appointmentId) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);

        if (appointment.isPresent()) {
            return AppointmentService.toResponse(appointment.get());
        }

        throw new EntityNotFoundException("Appointment with id " + appointmentId + " not found");
    }

    @Override
    @Transactional
    public void updateSlot(UUID appointmentId, UpdateAppointmentSlotRequest request) {

        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);

        if (appointment.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + appointmentId + " not found");
        }

        if (request.startTime() == null || request.startTime().isBefore(LocalDateTime.now())
                || request.endTime() == null || request.endTime().isBefore(LocalDateTime.now())) {
            throw new AppException(
                    MessageConstants.SLOT_INVALID,
                    "Please specify startTime / endTime in future");
        }

        if (request.startTime().isAfter(request.endTime())) {
            throw new AppException(
                    MessageConstants.SLOT_INVALID,
                    "Please select endDate ahead of startDate");
        }

        Appointment _appointment = appointment.get();

        _appointment.setStartTime(request.startTime());
        _appointment.setEndTime(request.endTime());

        appointmentRepo.save(_appointment);
    }

    @Override
    @Transactional
    public void updateStatus(UUID appointmentId, UpdateAppointmentStatusRequest request) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);

        if (appointment.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + appointmentId + " not found");
        }

        appointment.get().setStatus(request.status());

        appointmentRepo.save(appointment.get());
    }

    @Override
    public void delete(UUID appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }

    @Override
    public Page<GetAppointmentResponse> search(SearchAppointmentRequest request, Pageable pageable) {
        throw new NotImplementedException();
    }
}
