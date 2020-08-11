package com.parameta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.parameta.controller.Empleado;
import com.parameta.controller.EmpleadoControllerImplService;
import com.parameta.controller.Response;
import com.parameta.model.dto.EmpleadoDTO;
import com.parameta.ws.model.RestResponse;

@Service
public class EmpleadoService {

	public RestResponse guardarEmpleadoService(EmpleadoDTO dto)
			throws DatatypeConfigurationException {

		RestResponse restResponse = new RestResponse();

		int codigo = 0;
		String descripcion = "Empleado Almacenado Exitosamente!!";

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		EmpleadoControllerImplService servicioSoap = new EmpleadoControllerImplService();
		com.parameta.controller.EmpleadoController consumer = servicioSoap.getEmpleadoControllerImplPort();

		Empleado empleado = new Empleado();

		GregorianCalendar calendar = new GregorianCalendar();

		Date fechaNacimientoDate = null;
		Date fechaVinculacionDate = null;
		Date fechaHoyDate = new Date();

		XMLGregorianCalendar fechaNacimientoXML = null;
		XMLGregorianCalendar fechaVinculacionXML = null;

		Double salarioDouble = null;

		int anios = 0, meses = 0, residuoDias = 0, edad = 0;

		if (dto.getNumeroDocumento().equals("N/A")) {
			codigo = 1;
			descripcion = "Se debe ingresar el numero de documento del empleado";
		}

		if (dto.getNombres().equals("N/A")) {
			codigo = 1;
			descripcion = "Se debe ingresar el nombre del empleado";
		}

		if (dto.getApellidos().equals("N/A")) {
			codigo = 1;
			descripcion = "Se debe ingresar el apellido del empleado";
		}

		if (dto.getTipoDoc().equals("N/A")) {
			codigo = 1;
			descripcion = "Se debe ingresar el tipo de documento del empleado";
		}

		if (dto.getCargo().equals("N/A")) {
			codigo = 1;
			descripcion = "Se debe ingresar el cargo del empleado";
		}

		try {
			fechaNacimientoDate = formato.parse(dto.getFecha_nacimiento());
			calendar.setTime(fechaNacimientoDate);
			fechaNacimientoXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (ParseException ex) {
			codigo = 1;
			descripcion = "La fecha de nacimiento es requerida y debe ser con el formato dd/MM/yyyy";
		}

		try {
			fechaVinculacionDate = formato.parse(dto.getFecha_vinculacion());
			calendar.setTime(fechaVinculacionDate);
			fechaVinculacionXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (ParseException ex) {
			codigo = 1;
			descripcion = "La fecha de vinculacion es requerida y debe ser con el formato dd/MM/yyyy";
		}

		try {
			salarioDouble = new Double(dto.getSalario());
		} catch (Exception e) {
			codigo = 1;
			descripcion = "El salario es requerido solamente debe contener numeros y la separacion de decimal con punto ejemplo: 4500000.45";
		}

		if (codigo == 0) {
			double tiempoHoy = fechaHoyDate.getTime();
			double tiempoVinculacion = fechaVinculacionDate.getTime();
			double tiempoNacimiento = fechaNacimientoDate.getTime();

			double diferenciaTiempo = tiempoHoy - tiempoVinculacion;

			if (diferenciaTiempo > 0) {
				double dias = (((diferenciaTiempo / 1000) / 60) / 60) / 24;
				anios = (int) (dias / 365);
				double restanteAnio = (dias % 365) - anios;
				meses = (int) (restanteAnio / 30.4375);
				residuoDias = (int) (restanteAnio % 30.4375) - meses;
				if (residuoDias < 0) {
					residuoDias = 0;
				}

				diferenciaTiempo = tiempoHoy - tiempoNacimiento;
				if (diferenciaTiempo > 0) {
					edad = (int) ((((diferenciaTiempo / 1000) / 60) / 60) / 24) / 365;

					if (edad < 18) {
						codigo = 1;
						descripcion = "El empleado es menor de edad, no se puede almacenar.";
					}
				} else {
					codigo = 1;
					descripcion = "La fecha de nacimiento no puede ser mayor que la fecha actual.";
				}
			} else {
				codigo = 1;
				descripcion = "La fecha de vinculacion no puede ser mayor que la fecha actual.";
			}

		}

		if (codigo == 0) {
			empleado.setNumeroDocumento(dto.getNumeroDocumento());
			empleado.setNombres(dto.getNombres());
			empleado.setApellidos(dto.getApellidos());
			empleado.setTipoDocumento(dto.getTipoDoc());
			empleado.setFechaNacimiento(fechaNacimientoXML);
			empleado.setFechaVinculacion(fechaVinculacionXML);
			empleado.setCargo(dto.getCargo());
			empleado.setSalario(salarioDouble);

			Response responseSoap = consumer.almacenarEmpleado(empleado);

			restResponse.setCodigo(responseSoap.getCodigo());
			restResponse.setDescripcion(responseSoap.getDescripcion());
			restResponse.setTiempoVinculacion(
					"Tiempo Vinculacion: " + anios + " YY / " + meses + " MM / " + residuoDias + " DD .");
			restResponse.setEdad(edad);
		} else {
			restResponse.setCodigo(codigo);
			restResponse.setDescripcion(descripcion);
		}

		return restResponse;
	}

}
