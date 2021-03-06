//==============================================================================
// This software is part of the Open Standard for Unattended Sensors (OSUS)
// reference implementation (OSUS-R).
//
// To the extent possible under law, the author(s) have dedicated all copyright
// and related and neighboring rights to this software to the public domain
// worldwide. This software is distributed without any warranty.
//
// You should have received a copy of the CC0 Public Domain Dedication along
// with this software. If not, see
// <http://creativecommons.org/publicdomain/zero/1.0/>.
//==============================================================================
package mil.dod.th.ose.core.factory.api;

import java.util.Map;
import java.util.UUID;

import mil.dod.th.core.factory.FactoryObject;
import mil.dod.th.core.factory.FactoryObjectContext;
import mil.dod.th.core.factory.FactoryObjectProxy;
import mil.dod.th.ose.core.pm.api.PowerManagerInternal;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.event.EventAdmin;

/**
 * Contains internal functions available to the {@link FactoryObject} implementation. Includes functions that should not
 * be part of the {@link FactoryObject} interface available to outside consumers, but is needed internally.
 */
public interface FactoryObjectInternal extends FactoryObject, FactoryObjectContext
{
    /**
     * Key used for service properties internally by the core. Can be used to bind components to the property service 
     * type.
     */
    String SERVICE_TYPE_KEY = "those.service.type";
    
    /**
     * Attribute constant used to acquire the attributes of an objects factory.
     */
    String ATTRIBUTES_CLASS_SUFFIX = "Attributes";
    
    /**
     * Method used to initialize instance.
     * @param registry
     *  the {@link FactoryRegistry} to use
     * @param proxy
     *  the {@link FactoryObjectProxy} to use
     * @param factory
     *  factory that produced this instance
     * @param configAdmin
     *  the {@link ConfigurationAdmin} service that is to be used
     * @param eventAdmin
     *  the {@link EventAdmin} service that is to be used
     * @param powerMgr
     *  the {@link PowerManagerInternal} service that is to be used
     * @param uuid
     *  the {@link UUID} of the {@link FactoryObject}
     * @param name
     *  the name of the {@link FactoryObject}
     * @param pid
     *  the PID of the {@link FactoryObject}
     * @param baseType
     *  the base type of the {@link FactoryObject}
     * @throws IllegalStateException
     *  if unable to retrieve attribute metadata interface
     */
    void initialize(FactoryRegistry<?> registry, FactoryObjectProxy proxy, final FactoryInternal factory, //NOPMD: 
            final ConfigurationAdmin configAdmin, final EventAdmin eventAdmin, final PowerManagerInternal powerMgr,
            UUID uuid, String name, String pid, String baseType) throws IllegalStateException;
            //ExcessiveParameterList, needed to provide this class with access to necessary services.    
    /**
     * Set the persistence ID for the object. The PID is generated by {@link org.osgi.service.cm.ConfigurationAdmin}
     * when the configuration is created.
     * 
     * @param pid
     *            Persistence ID for the asset
     */
    void setPid(String pid);
    
    /**
     * Updates the cached name of the factory object.
     * 
     * @param name
     *            the desired name for the object
     */
    void internalSetName(String name);
    
    /**
     * Get the base type of the object (asset, physical link, etc.). 
     * @return 
     *  the base type of the object
     */
    String getBaseType();
    
    /**
     * Method to retrieve the {@link FactoryObjectProxy} for this {@link FactoryObject}.
     * @return
     *  the {@link FactoryObjectProxy} instance
     */
    FactoryObjectProxy getProxy();
    
    /**
     * Notify the object that its configuration properties have been updated via 
     * {@link org.osgi.service.cm.ConfigurationAdmin}. This method will call {@link 
     * mil.dod.th.core.factory.FactoryObjectProxy#updated(Map)} and block until the updating process is complete. 
     * 
     * This method will block those that update using {@link #setProperties(java.util.Map)} and therefore, 
     * should do minimal processing
     *
     * @param props
     *      updated property values
     * @throws ConfigurationException 
     *      if one of the new property values is invalid
     */
    void blockingPropsUpdate(Map<String, Object> props) throws ConfigurationException;

    /**
     * The object's config that is retrievable via {@link #getProperties()} has been updated.
     * @param props
     *  the new properties
     * @throws ConfigurationException 
     *      if one of the new property values is invalid
     */
    void configUpdated(final Map<String, Object> props) throws ConfigurationException;
    
    @Override 
    FactoryInternal getFactory();
}
