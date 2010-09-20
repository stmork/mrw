package de.morknet.mrw.base;

import de.morknet.mrw.Modell;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse verwaltet eine Lampe.
 * @author smork
 *
 */
public class Lampe extends DeviceUnit
{
	private static final long serialVersionUID = 1L;

	private int schwellwert;

	private int type;

	/**
	 * Initialisiert diese Lampe.
	 * @param modell Die Modelleisenbahn, auf der die Lampe angebracht ist.
	 * @param name Der Name der Lampe.
	 */
	public Lampe(Modell modell, String name)
	{
		super(modell, name);
	}

	/**
	 * Gibt den Schwellwert zurück, ab dem das Licht bei Unterschreiten einer Helligkeit eingeschaltet wird.
	 * @return Der Helligkeitsschwellwert.
	 */
	public int getSchwellwert()
	{
		return schwellwert;
	}

	/**
	 * Setzt den Schwellwert, ab dem das Licht bei Unterschreiten einer Helligkeit eingeschaltet werden soll. 
	 * @param schwellwert Der Helligkeitsschwellwert.
	 */
	public void setSchwellwert(int schwellwert)
	{
		this.schwellwert = schwellwert;
	}

	/**
	 * Gibt den Lampentyp zurück.
	 * @return Der Lampentyp.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Setzt den Typ der Lampe.
	 * @return Der neue Lampentyp.
	 */
	public void setType(int type)
	{
		this.type = type;
	}

	@Override
	public MrwMessage createConfigMessage()
	{
		MrwMessage msg = MrwMessage.createCommandMsg(Command.CFGLGT, ctrl_id, unit_no);

		msg.addDataByte(pin);
		msg.addDataByte(schwellwert);
		msg.addDataByte(type);
		return msg;
	}

	@Override
	protected Command getCommand()
	{
		return null;
	}

}
