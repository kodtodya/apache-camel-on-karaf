package com.kodtodya.training.fuse;

import org.apache.camel.CamelContext;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import com.kodtodya.training.fuse.routes.TimerRoute;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.ArrayList;

@Component(
        name = "timer-log-example",
        immediate = true
)
public class CamelOsgiDemo {

    private BundleContext bundleContext;
    private ModelCamelContext camelContext;
    private ServiceRegistration<CamelContext> serviceRegistration;

    @Activate
    public void activate(ComponentContext componentContext) throws Exception {
        bundleContext = componentContext.getBundleContext();
        camelContext = new OsgiDefaultCamelContext(bundleContext);
        serviceRegistration = bundleContext.registerService(CamelContext.class, camelContext, null);
        camelContext.start();
        camelContext.addRoutes(new TimerRoute());
    }

    @Deactivate
    public void deactivate() throws Exception {
        camelContext.stop();
        camelContext.removeRouteDefinitions(new ArrayList<RouteDefinition>(camelContext.getRouteDefinitions()));
        bundleContext.ungetService(serviceRegistration.getReference());
    }

}
