/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */


package fr.paris.lutece.plugins.pluginwizard.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class provides Data Access methods for LocalizationKey objects
 */

public final class LocalizationKeyDAO implements ILocalizationKeyDAO
{
	
	// Constants
	
	private static final String SQL_QUERY_NEW_PK = "SELECT max( id_key ) FROM pluginwizard_localization_key";
	private static final String SQL_QUERY_SELECT = "SELECT id_key, key_name, english_locale, french_locale FROM pluginwizard_localization_key WHERE id_key = ?";
	private static final String SQL_QUERY_INSERT = "INSERT INTO pluginwizard_localization_key ( id_key, key_name, english_locale, french_locale ) VALUES ( ?, ?, ?, ? ) ";
	private static final String SQL_QUERY_DELETE = "DELETE FROM pluginwizard_localization_key WHERE id_key = ? ";
	private static final String SQL_QUERY_UPDATE = "UPDATE pluginwizard_localization_key SET id_key = ?, key_name = ?, english_locale = ?, french_locale = ? WHERE id_key = ?";
	private static final String SQL_QUERY_SELECTALL = "SELECT id_key, key_name, english_locale, french_locale FROM pluginwizard_localization_key";


	
	/**
	 * Generates a new primary key
         * @param plugin The Plugin
	 * @return The new primary key
	 */
    
	public int newPrimaryKey( Plugin plugin)
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
		daoUtil.executeQuery();

		int nKey;

		if( !daoUtil.next() )
		{
			// if the table is empty
			nKey = 1;
		}

		nKey = daoUtil.getInt( 1 ) + 1;
		daoUtil.free();

		return nKey;
	}




	/**
	 * Insert a new record in the table.
	 * @param localizationKey instance of the LocalizationKey object to insert
         * @param plugin The plugin
	 */

	public void insert( LocalizationKey localizationKey, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT , plugin );
                
		localizationKey.setIdKey( newPrimaryKey( plugin ) );
                
                daoUtil.setInt ( 1, localizationKey.getIdKey ( ) );
                daoUtil.setString ( 2, localizationKey.getKeyName ( ) );
                daoUtil.setString ( 3, localizationKey.getEnglishLocale ( ) );
                daoUtil.setString ( 4, localizationKey.getFrenchLocale ( ) );

		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Load the data of the localizationKey from the table
	 * @param nId The identifier of the localizationKey
         * @param plugin The plugin
	 * @return the instance of the LocalizationKey
	 */


        public LocalizationKey load( int nId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT , plugin );
		daoUtil.setInt( 1 , nId );
		daoUtil.executeQuery();

		LocalizationKey localizationKey = null;

		if ( daoUtil.next() )
		{
			localizationKey = new LocalizationKey();

                        localizationKey.setIdKey( daoUtil.getInt(  1 ) );
                        localizationKey.setKeyName( daoUtil.getString(  2 ) );
                        localizationKey.setEnglishLocale( daoUtil.getString(  3 ) );
                        localizationKey.setFrenchLocale( daoUtil.getString(  4 ) );
		}

		daoUtil.free();
		return localizationKey;
	}


	/**
	 * Delete a record from the table
         * @param nLocalizationKeyId The identifier of the localizationKey
         * @param plugin The plugin
	 */

	public void delete( int nLocalizationKeyId, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE , plugin );
		daoUtil.setInt( 1 , nLocalizationKeyId );
		daoUtil.executeUpdate();
		daoUtil.free();
	}


	/**
	 * Update the record in the table
	 * @param localizationKey The reference of the localizationKey
         * @param plugin The plugin
	 */

	public void store( LocalizationKey localizationKey, Plugin plugin )
	{
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE , plugin );
                
        daoUtil.setInt( 1, localizationKey.getIdKey( ) );
        daoUtil.setString( 2, localizationKey.getKeyName( ) );
        daoUtil.setString( 3, localizationKey.getEnglishLocale( ) );
        daoUtil.setString( 4, localizationKey.getFrenchLocale( ) );
        daoUtil.setInt( 5, localizationKey.getIdKey( ) );
                
		daoUtil.executeUpdate( );
		daoUtil.free( );
	}



	/**
	 * Load the data of all the localizationKeys and returns them as a collection
         * @param plugin The plugin
	 * @return The Collection which contains the data of all the localizationKeys
	 */

        public Collection<LocalizationKey> selectLocalizationKeysList( Plugin plugin )
	{
		Collection<LocalizationKey> localizationKeyList = new ArrayList<LocalizationKey>(  );
		DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL , plugin );
		daoUtil.executeQuery(  );

		while ( daoUtil.next(  ) )
		{
                LocalizationKey localizationKey = new LocalizationKey(  );

                    localizationKey.setIdKey( daoUtil.getInt( 1 ) );
                    localizationKey.setKeyName( daoUtil.getString( 2 ) );
                    localizationKey.setEnglishLocale( daoUtil.getString( 3 ) );
                    localizationKey.setFrenchLocale( daoUtil.getString( 4 ) );

                localizationKeyList.add( localizationKey );
		}

		daoUtil.free();
		return localizationKeyList;
	}

}