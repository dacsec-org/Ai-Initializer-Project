import React, { useState, useEffect } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Grid, GridColumn, Notification } from '@vaadin/react-components';
import { SearchModels } from '../search/SearchModels';
import { DownloadAction } from '../../enums/DownloadAction';

export const config: ViewConfig = {
  menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg', title: 'Search Models' }
};

interface SearchModelsViewProps {
  searchQuery: string;
  onModelsFetched: (models: any[]) => void;
  onLoading: (loading: boolean) => void;
}

const SearchModelsView: React.FC<SearchModelsViewProps> = ({ searchQuery, onModelsFetched, onLoading }) => {
  const [models, setModels] = useState<any[]>([]);

  useEffect(() => {
    if (searchQuery) {
      onLoading(true);

      SearchModels.getModels(DownloadAction.SEARCH, searchQuery)
        .then(response => response.json())
        .then(jsonResponse => {
          setModels(jsonResponse);
          onModelsFetched(jsonResponse);
          Notification.show('Models fetched successfully');
        })
        .finally(() => {
          onLoading(false);
        });
    }
  }, [searchQuery, onLoading, onModelsFetched]);

  const handleDownload = async (id: string) => {
    await SearchModels.getModels(DownloadAction.DOWNLOAD_LLM_MODEL, id)
      .then(() => {
        Notification.show('Model downloaded successfully');
      });
  };

  return (
    <Grid items={models} columnReorderingAllowed style={{ height: '100vh', width: '100%' }}>
      <GridColumn
        header="Download"
        renderer={({ item }) => (
          <Button onClick={() => handleDownload(item.id)} theme="icon" aria-label="Download">
            ⬇️
          </Button>
        )}
      />
      <GridColumn path="id" header="ID" resizable />
      <GridColumn path="modelId" header="Model ID" resizable />
      <GridColumn path="likes" header="Likes" resizable />
      <GridColumn path="trendingScore" header="Trending Score" resizable />
      <GridColumn path="downloads" header="Downloads" resizable />
      <GridColumn path="pipelineTag" header="Pipeline Tag" resizable />
      <GridColumn path="libraryName" header="Library Name" resizable />
      <GridColumn path="createdAt" header="Created At" resizable />
    </Grid>
  );
};

export default SearchModelsView;
