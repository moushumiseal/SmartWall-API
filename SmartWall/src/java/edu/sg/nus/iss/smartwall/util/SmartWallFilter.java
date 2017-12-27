/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.util;

import java.io.IOException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Moushumi Seal
 */
@Provider
@PreMatching
public class SmartWallFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("*******Request:="+requestContext.getUriInfo().getRequestUri());
        System.out.println("*******Request:="+requestContext.getUriInfo().getPathParameters());
        System.out.println("*******Request:="+requestContext.getUriInfo().getQueryParameters());
        System.out.println("*******Request:="+requestContext.getEntityStream().toString());
        System.out.println(requestContext.getPropertyNames());
        String authHeader = requestContext.getHeaderString("smartwallheader");
        if (authHeader == null || !authHeader.equals("smartwall")) {
            throw new NotAuthorizedException("Illegal Access");
        }
    }

}
